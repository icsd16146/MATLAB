function dictionary = CreateDictionary(stringArray)
    dictionary=["1";"2"];
    k=1;%θεση στο λεξικο
    for i = 1:length(stringArray)%για καθε reddit post
        splitstring = split(stringArray(i,1));%δημιουργει πινακα λεξεων

        for j = 1:length(splitstring)%για κάθε λέξη του πινακα i
            if contains(dictionary,splitstring(j,1))==0%ελέγχει αν η λέξη j είναι στο λεξικο
                dictionary(k,1)=splitstring(j,1);%αν όχι, τοτε αποθηκευεται στο λεξικο
                k=k+1;%επομενη θεση στο λεξικο
            end
        end
    end
end