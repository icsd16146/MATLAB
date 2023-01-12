function [sortedPosts,sortedDates] = sortPosts(redditCreatedutc,redditPosts)

    for i = 1 : length(redditCreatedutc)
        i
        m = min(redditCreatedutc);%ευρεση μικροτερου αριθμου(pseudoημερομηνιες)
        row = find(redditCreatedutc==m);%ευρεση γραμμης του post
        if length(row)>1%σε περιπτωση που δυο post εχουν τον ιδιο αριθμο δημιουργιας
            minRow = min(row);%παρε την πιο μικρη γραμμη

            postj = redditPosts(minRow,1);
            Dates(i,1) = m;
            Posts(i,1) =postj;
            redditCreatedutc(minRow,1) = 1000000000000000;     
        else
            post = redditPosts(row,1)%ευρεση του post
            Dates(i,1) = m;%αποθηκεσυη pseudoημερομηνιας
            Posts(i,1) =post;%αποθηκευση post
            redditCreatedutc(row,1) = 1000000000000000;%αντικατασταση αριθμου στον αρχικο πινακα 
            % με πολυ μεγαλο αριθμο για ευκολη ευρεση του επομενου μικροτερου αριθμου
        end
    end
    %assignin('base','sortedPosts',Posts);%assign Posts to sortedPosts in Workspace
    %assignin('base','sortedDates',Dates);%assign Created_uts nums to sortedDates in Workspace

    sortedPosts = Posts;
    sortedDates = Dates;
end
