function newLevelUpMoves = newLvlUMov(levelUpMoves,listNatDexPokemon)
    %προβλημα με την writematrix!!!!!!!!!!!!
    newLevelUpMoves=["0","1","2","3","4","5"];
    elligible = false;
    k=1;
    for i = 1:size(levelUpMoves)                                    %για καθε γραμμη στο αρχειο levelUpMoves
        isName =  sum(isletter(levelUpMoves(i,1)));                 %ελεγχω αν η γραμμη ειναι ονομα ή αριθμος
        %if isName>1 isName=1;  disp('true'); end
        if isName == 0                                              %αν ειναι αριθμος(level) συνεχιζουμε αυτο που καναμε
            elligible = elligible;
        else
            tf = sum(contains(listNatDexPokemon,levelUpMoves(i,1)))
            if tf                                                    %αν το ποκεμον ανηκει στην λιστα με τα ποκεμον που θα χρησιμοποιησω
                disp('true');elligible = true;                       %μπορουν να αποθηκευτουν οι επομενες γραμμες βασει του επομενου if
            else                                                     %αλλιως δεν μπαινουν οι γραμμες
                elligible = false;
            end
        end
        if elligible
            newLevelUpMoves(k,:) = levelUpMoves(i,1:6);             %αποθηκευση γραμμης για το elligible ποκεμον
            k=k+1;                                                  %αλλαζω γραμμη στον καινο πινακα
        end
    end
end