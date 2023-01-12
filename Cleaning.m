function [tokenizedPosts] = Cleaning(posts)
      
    t = eraseURLs(posts);
    t = lower(t);
        %t = tall(t);
        t = tokenizedDocument(t);
    t = erasePunctuation(t);
    t = correctSpelling(t);
    t = removeStopWords(t);
    t = addPartOfSpeechDetails(t);
    t = normalizeWords(t,'Style','lemma');
    t = removeShortWords(t,2);
        
    tokenizedPosts = tokenizedDocument(t); 
    %assignin('base','tokenPosts',tokenizedPosts);%assign values to tokenPosts in Workspace
end