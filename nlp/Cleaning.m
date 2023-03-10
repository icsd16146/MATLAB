function [tokenizedPosts] = Cleaning(posts)
      
    t1 = eraseURLs(posts);
        disp('1')
    t2 = lower(t1);
 
    t3 = tokenizedDocument(t2);
        disp('2')
        clear("t1"); clear("t2");
    t4 = erasePunctuation(t3);
        disp('3')
        clear("t3");
    %t5 = correctSpelling(t4);
    ta=correctSpelling(t4(1:100000));
        disp('a')
    tb = correctSpelling(t4(100001:200000));
        disp('b')
    tc = correctSpelling(t4(200001:300000));
        disp('c')
    td = correctSpelling(t4(300001:374551));
        disp('d')
    t5=[ta;tb;tc;td];
        disp('4')
        clear("t4");
    t6 = removeStopWords(t5);
        disp('5')
        clear("t5");
    t7 = addPartOfSpeechDetails(t6);
        disp('6')
        clear("t6");
    t8 = normalizeWords(t7,'Style','lemma');
        clear("t7");
    t9 = removeShortWords(t8,2);
        disp('7')
        clear("t8");
        
    tokenizedPosts = tokenizedDocument(t9);
        disp('8')
        clear("t9");
    %assignin('base','tokenPosts',tokenizedPosts);%assign values to tokenPosts in Workspace
end