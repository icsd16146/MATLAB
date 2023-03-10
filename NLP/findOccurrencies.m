function numOfInstances = findOccurrencies(dictionary,stringArray)
    numOfInstances=zeros(length(dictionary),1);%πινακας με τους αριθμους εμφανισης καθε λεξης του λεξικου
    
    for i =1:length(dictionary)%για καθε λεξη στο λεξικο
        str = dictionary(i,1)
        for j=1:length(stringArray)%παρε καθε reddit post
            instances = strfind(stringArray(j,1),dictionary(i,1));%βρες σε ποια σημεια εμφανιζεται η λεξη
            numOfInstances(i,1) = numOfInstances(i,1)+ length(instances);%ο αριθμος σημειων εμφανισης αποθηκευεται
        end
    end
end