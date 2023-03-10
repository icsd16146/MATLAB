function covidSpec = NeuralNetwork(patients,weights1,covidResults)

    %patients = patients(:,1:15);
    %weights1 = weights1(:,1:15);
    %covidResults = covidResults(:,1);

    weights2 = [1,1,1,1,1,1,0,1,1,0,0,1,1,1,1,1,0,0,0,1,0;%γριπη
                1,1,1,1,1,0,0,1,1,0,0,1,0,1,1,0,1,1,1,1,1;%βακτηριακη πνευμονια
                1,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0;%παραγριπη
                1,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,1,1,0;%αλλος κορονοϊος
                1,1,0,0,0,0,0,0,1,0,0,0,0,0,1,1,1,0,0,0,1%πνευμονικος ιος
                ];
    weights3 = [0;%fever or chills
                0;%cough
                0;%difficulty breathing
                0;%fatigue
                0;%body aches
                0;%headache
                0;%loss taste/smell
                0;%sore throat
                0;%runny nose
                0;%nausea/vomitting
                0;%diarrhea
                0;%confusion
                0;%inability to wake/stay awake
                0;%pale skin/lips/nailbeds
                0;%εντονος ιδρωτας,εφιδρωση
                0;%κοπωση,αδυναμια,κομαρες,κακουχια
                0;%φλεγματα
                0;%αδιαθεσια
                0;%απωλεια ορεξης
                0;%πονοι στις αρθρωσεις
                0;%αιμοπτυση
                ];
    
    covidSpec(length(covidResults),1)=zeros();
    changes=0;
    bias=1;
    for i = 1:length(covidResults)
        i%αριθμος ασθενη
        patient = patients(i,:);%εξετασεις ασθενους

        categories = InputLayer(patients(i,:), weights1, bias);%εξετασεις->κατηγοριες

        symptoms = HiddenLayer(categories, weights2, bias);%κατηγορίες->συμπτωματα

        covid=covidResults(i)
        covidPrediction = OutputLayer(symptoms, weights3, bias)%συμπτωματα->αποτελεσματα

        covidSpec(i,1) = covidPrediction;%πινακας προβλεψεων δικτυου για ολους τους ασθενεις

        [weights3,changes] = CorrectingOutputLayer(covidResults(i),covidPrediction,weights3,symptoms,changes);%υπολογισμος καινων βαρων/εκμαθηση αλγοριθμου
    end

    writematrix(weights3);
    writematrix(covidSpec);

end