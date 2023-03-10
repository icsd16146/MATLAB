function sentimentScores = createSentimentScores(length1,length2)
    sentimentScores(length1+length2,1) = zeros();
    for i =1:length1
        sentimentScores(i) = rand();
    end 
    
    k=length1+1;
    for i = 1:length2
        sentimentScores(k) = ~rand();
        k=k+1;
    end
end