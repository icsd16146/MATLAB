function output = OutputLayer(input,weights, bias)

    output(:,1)=zeros();%αρχικοποιησει πινακα εξοδου

    sz = size(weights);%ευρεση μεγεθους πινακα με βαρη
    %symptoms(length(input),sz(1,2))=zeros();

    for i=1:sz(1,2)
        r = input.*weights(:,i);%πολλαπλασιασμος τιμων με αντιστοιχα βαρη
        %symptoms(:,i)=r;%αποθήκευση τιμών βαρών-εισόδων για το learning
        output(i,1) = sum(r)+bias*1;%τελικο αθροισμα και αποθηκευση στον πινακα εξοδου
        if output(i,1)>1
            output(i,1)=1;
        elseif output(i,1)<-1
            output(i,1)=-1;
        end
    end
end