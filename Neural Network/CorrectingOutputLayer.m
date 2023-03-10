function [newWeights,changes] = CorrectingOutputLayer(covidCase,NNoutput,weights,symptoms,changes)
%αρνητικο αποτελεσμα = δεν εχει κοβιντ
%θετικό αποτέλεσμα = εχει κοβιντ
    newWeights=weights;
    if covidCase == -1%αν ο ασθενης εχει αρνητικο κοβιντ
        if NNoutput>=0 %αλλα το δικτυο δειχνει πως δεν ξέρει ή ειναι θετικος
            %επροκειτο να αλλαξουν οι τιμες βαρών για τις ακμες που οδηγησαν στο παρων αποτελεσμα
            disp('wrong answer')
            changes=changes+1
            weights1=weights;%πινακας που θα αποθηκευτούν τα διαφορετικα βάρη
                        
            sz = size(weights);
            for i = 1:sz(1,1)%για ολες τις γραμμες του weights3
                if symptoms(i,1)== 1 %αν το σύμπτωμα οδηγει σε θετικο κοβιντ
                    weights1(i,1) = 0;%δεν συμπεριλαμβανεται στην διαγνωση
                elseif symptoms(i,1) == -1%αν οδηγει σε αρνητικο κοβιντ
                    weights1(i,1) = 1;%συμπεριλαμβανεται στην διαγνωση
                end
                %αν το σύμπτωμα δεν έχει συμπεριληφθεί στην εξαγωγή του αποτελέσματος
                %κράτα το ίδιο βάρος, γραμμη 10
            end
            newWeights=weights1%καινος πινακας με βαρη
        else
            disp('no change of weights required')
        end
    end
    if covidCase == 1%αν ο ασθενης εχει θετικο κοβιντ
        if NNoutput<=0%αλλα το δικτυο δειχνει πως δεν ξέρει ή ειναι αρνητικος
            %επροκειτο να αλλαξουν οι τιμες βαρών για τις ακμες που οδηγησαν στο παρων αποτελεσμα
            disp('wrong answer')
            changes=changes+1
            weights1=weights;%πινακας που θα αποθηκευτούν τα διαφορετικα βάρη
                        
            sz = size(weights);
            for i = 1:sz(1,1)%για ολες τις γραμμες του weights3
                if symptoms(i,1)== -1 %αν το σύμπτωμα οδηγει σε αρνητικο κοβιντ
                    weights1(i,1) = 0;%δεν περιλαμβανεται στην διαγνωση
                elseif symptoms(i,1) == 1 %αν οδηγει σε θετικο
                    weights1(i,1) = 1;%συμπεριλαμβανεται στην διαγνωση
                end
                %αν το σύμπτωμα δεν έχει συμπεριληφθεί στην εξαγωγή του αποτελέσματος
                %κράτα το ίδιο βάρος, γραμμη 32
            end
            newWeights=weights1%καινος πινακας με βαρη
        else
            disp('no change of weights required')
        end
    end
end