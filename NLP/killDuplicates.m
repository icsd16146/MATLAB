function [newArray,newDates] = killDuplicates(posts,dates)
    newArray=["1";"2"];
    newDates=[1;2];
    k=1;
    for i=1:length(posts)
        i
        if ~contains(newArray,posts(i))%αν το ποστ δεν βρισκεται στον πινακα
            newArray(k)=posts(i);
            newDates(k)=dates(i);
            k=k+1;
        end
    end
end
%η unique() δεν με συμφερει διοτι αλλαζει τυχαια θεση τα ποστ και εγω
%χρειαζομαι αντιστοιχιση με πινακα ημ/νιων