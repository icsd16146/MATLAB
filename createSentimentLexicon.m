function lexicon = createSentimentLexicon(tokenizedDoc,seedsPositive,seedsNegative)

    %train word embedding
    emb = trainWordEmbedding(tokenizedDoc,'Window',25,'MinCount',20);

    %create word graph
    numNeighbors = 7;
    vocabulary = emb.Vocabulary;
    wordVectors = word2vec(emb,vocabulary);

    [nearestWords,dist] = vec2word(emb,wordVectors,numNeighbors);

    sourceNodes = repelem(vocabulary,numNeighbors);
    targetNodes = reshape(nearestWords,1,[]);

    edgeWeights = reshape(dist,1,[]);

    wordGraph = graph(sourceNodes,targetNodes,edgeWeights,vocabulary);
    wordGraph = simplify(wordGraph);

    %%%%%%εμφανισε γραφο με επικεντρο τον κομβο losses
    %word = "science";
    %idx = findnode(wordGraph,word);
    %nbrs = neighbors(wordGraph,idx);
    %wordSubgraph = subgraph(wordGraph,[idx; nbrs]);
    %figure
    %plot(wordSubgraph)
    %title("Words connected to """ + word + """")
    %%%%%%

    %generate sentiment scores
    sentimentScores = zeros([1 numel(vocabulary)]);

    maxPathLength = 4;
    %calculate sum of sentiment scores
    for depth = 1:maxPathLength
    
        % Calculate polarity scores.
        polarityPositive = polarityScoresMATLAB(seedsPositive,vocabulary,wordGraph,depth);
        polarityNegative = polarityScoresMATLAB(seedsNegative,vocabulary,wordGraph,depth);
    
        % Account for difference in overall mass of positive and negative flow
        % in the graph.
        b = sum(polarityPositive) / sum(polarityNegative);
        
        % Calculate new sentiment scores.
        sentimentScoresNew = polarityPositive - b * polarityNegative;
        sentimentScoresNew = normalize(sentimentScoresNew,'range',[-1,1]);
    
        % Add scores to sum.
        sentimentScores = sentimentScores + sentimentScoresNew;
    end

    sentimentScores = sentimentScores / maxPathLength;
    %Create a table containing the vocabulary and the corresponding sentiment scores
    tbl = table;
    tbl.Token = vocabulary';
    tbl.SentimentScore = sentimentScores';
    %remove neutral words
    thr = 0.1;
    idx = abs(tbl.SentimentScore) < thr;
    tbl(idx,:) = [];
     
    tbl = sortrows(tbl,'SentimentScore','descend');%Sort the table rows by descending sentiment score
    head(tbl)%view the first few rows

    %visualize negative and positive lexicons
    %figure
    %subplot(1,2,1);
    %idx = tbl.SentimentScore > 0;
    %tblPositive = tbl(idx,:);
    %wordcloud(tblPositive,'Token','SentimentScore')
    %title('Positive Words')

    %subplot(1,2,2);
    %idx = tbl.SentimentScore < 0;
    %tblNegative = tbl(idx,:);
    %tblNegative.SentimentScore = abs(tblNegative.SentimentScore);
    %wordcloud(tblNegative,'Token','SentimentScore')
    %title('Negative Words')

    %return lexicon
    lexicon=tbl;

    %filename = "climateChangeSentimentLexicon.csv";
    %writetable(tbl,filename)    
end