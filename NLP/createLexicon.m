function lexicon = createLexicon(words,scores)
    %Create a table containing the vocabulary and the corresponding sentiment scores
    tbl = table;
    tbl.Token = words;
    tbl.SentimentScore = scores;
    %remove neutral words
    thr = 0.1;
    idx = abs(tbl.SentimentScore) < thr;
    tbl(idx,:) = [];
     
    tbl = sortrows(tbl,'SentimentScore','descend');%Sort the table rows by descending sentiment score
    head(tbl)%view the first few rows
    lexicon=tbl;
end