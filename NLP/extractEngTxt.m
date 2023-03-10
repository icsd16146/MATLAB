function [eng,newDates] = extractEngTxt(posts,dates)
    eng(2,1)=["1"];
    newDates=[1;2];

    j=1;
    for i =1:length(posts)
        language = corpusLanguage(posts(i));%ευρεση γλωσσας ποστ
        if language == 'en'
          j
            eng(j)=posts(i);
            newDates(j)=dates(i);
            j=j+1;
        end
    end
end