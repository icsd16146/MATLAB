function output = InputLayer(testResults, weights1, bias)
    sz = size(weights1);%εύρεση μεγέθους πίνακα βαρών
    output(sz(1,1),1)=zeros();%αρχικοποίηση πίνακα όπου οι γραμμές του είναι ίσες με τον πίνακα βαρών

    for i = 1:sz(1,1)
        %testResults
        %w=weights1(i,:)
        r = testResults.*weights1(i,:);%πολλαπλασιασμός τιμών με αντίστοιχα βάρη
        output(i,1) = sum(r)+bias*-1;%άθροισμα αποτελεσμάτων μαζί με bias
        if output(i,1)>1
            output(i,1)=1;
        elseif output(i,1)<-1
            output(i,1)=-1;
        end
    end
end