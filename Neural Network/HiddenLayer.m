function output = HiddenLayer(input,weights, bias)
    sz = size(weights);
    output(sz(1,2),1)=zeros();%αρχικοποιηση πινακα εξοδου

    for i=1:sz(1,2)
        r = input.*weights(:,i);%πολλαπλασιασμος τιμων με αντιστοιχα βαρη
        output(i,1) = sum(r)+bias*1;%τελικο αθροισμα και αποθηκευση στον πινακα εξοδου
        if output(i,1)>1
            output(i,1)=1;
        elseif output(i,1)<-1
            output(i,1)=-1;
        end
    end
end