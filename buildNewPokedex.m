function newPokedex = buildNewPokedex(pokemon,listNatDexPokemon)
    newPokedex=["0","1"];
    elligible = false;
    k=1;
    for i = 1:size(pokemon)                             %για καθε γραμμη στο αρχειο ποκεμον του pokemon essentials
        if contains(pokemon(i,1),"[")                   %αν η γραμμη εχει το ονομα του ποκεμον
            name1 = erase(pokemon(i,1),"[");
            name = erase(name1,"]"); display(name)
            tf = sum(contains(listNatDexPokemon,name));
           if tf                                        %αν το ποκεμον ανηκει στην λιστα με τα ποκεμον που θα χρησιμοποιησω
                elligible = true;                       %μπορουν να αποθηκευτουν οι επομενες γραμμες βασει του επομενου if
            else                                        %αλλιως δεν μπαινουν οι γραμμες
                elligible = false;
            end
        end
        if elligible
            newPokedex(k,:) = pokemon(i,:);             %αποθηκευση γραμμης για το elligible ποκεμον
            k=k+1;                                      %αλλαζω γραμμη στον καινο πινακα
        end
    end
end