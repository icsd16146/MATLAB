function VisualizeWords(tokenizedDocs)
 bag = bagOfNgrams(d8,NGramLengths=2);

 %word cloud visualization 
 figure
 wordcloud(bag);
 title("Text Data: Preprocessed Bigrams")
 tbl = topkngrams(bag,10)

 %term frequency matrix
 M = tfidf(bag);
 full(M(1:10,1:10))
end