function timeTable = ScoresxTime(dates,scores,periods)
    dates = dates./10000000;
    
    timeTable = zeros(length(periods),1);%αρχικοποιηση πινακα

    for i =1:length(timeTable)-1
        numArray = findInbetween(dates,periods(i),periods(i+1))%πινακας συγκεκριμενης χρονικης περιοδου

        mn = min(numArray)%μικροτερη ημ/νια
        mx = max(numArray)%μεγιστη ημ/νια
        RowIdx1 = find(ismember(dates, mn,'rows'))%ευρεση γραμμης μικροτερης ημ/νιας
        RowIdx2 = find(ismember(dates, mx,'rows'))%ευρεση γραμμης μεγαλυτερης ημ/νιας
        %row1 = find(dates,mn);%ευρεση γραμμης μικροτερης ημ/νιας
        %row2 = find(dates,mx);%ευρεση γραμμης μεγαλυτερης ημ/νιας
        timeTable = scores(RowIdx1:RowIdx2);%αποθηκευση συνολικου συναισθηματος συγκεκριμενης χρονικης περιοδου
    end
end