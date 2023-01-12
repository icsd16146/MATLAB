function SentimentAnalysis(tokenizedDocs)
 %sentiment analysis
 compoundScores = vaderSentimentScores(tokenizedDocs);
 compoundScores(1:5)
 idx = compoundScores > 0;
 strPositive = str(idx);
 strNegative = str(~idx);

 %visualize sentiment with word clouds %visualize*********
 figure
 subplot(1,2,1)
 wordcloud(strPositive);
 title("Positive Sentiment")

 subplot(1,2,2)
 wordcloud(strNegative);
 title("Negative Sentiment")

 %word embedding
 emb = fastTextWordEmbedding;

 %loading opinion lexicon
 data = readLexicon;

 %prepare data for training
 idx = ~isVocabularyWord(emb,data.Word);%αφαιρεση λεξεων απο το emb που δεν ανηκουν στο λεξικο
 data(idx,:) = [];%convert words to word vectors
    %απομονωση
    numWords = size(data,1);
    cvp = cvpartition(numWords,'HoldOut',0.1);%απομονωση 10% των λεξεων
    dataTrain = data(training(cvp),:);
    dataTest = data(test(cvp),:);%γινεται μικρο τεστ για υποδειξη

 wordsTrain = dataTrain.Word;
 XTrain = word2vec(emb,wordsTrain);%μετατροπη των λεξεων σε word vector
 YTrain = dataTrain.Label;
 mdl = fitcsvm(XTrain,YTrain);%κατηγοριοποιηση word vector σε θετικων κ αρνητικων

 wordsTest = dataTest.Word;
 XTest = word2vec(emb,wordsTest);%μετατροπη αποτελεσματων του data test σε word vectors
 YTest = dataTest.Label;

 %προβλεψη
 [YPred,scores] = predict(mdl,XTest);
 %visualize***************
 figure
 confusionchart(YTest,YPred);%κατηγοριοποιηση σε confusion matrix

 %sentiment calculation
 for i = 1:numel(documents)%για καθε doc
    words = string(documents(i));%παρε καθε λεξη του doc
    vec = word2vec(emb,words);%δημιουργησε word vectors
    [~,scores] = predict(mdl,vec);%προβλεψε το sentiment score στους vectors 
    sentimentScore(i) = mean(scores(:,1));%μετατροπη score σε mean sentiment score
 end
 table(sentimentScore', textData)%εμφανιση score, οσο πιο μεγαλος αριθμος, τοσο πιο θετικο
end