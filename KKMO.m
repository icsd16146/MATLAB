function kkmo = KKMO (Y,m)
  T1=0; T2=0; T3=(T1+T2)/2; %αρχικοποιηση των Τ
  kkmo=zeros(length(Y),1); %δημιουργια πινακα κμο
  start=m/2;
  finish= length(Y)-start-1;
  for(i=start:finish)
    T1=(Y(i-5)+Y(i-4)+Y(i-2)+Y(i-3)+Y(i-1)+Y(i)+Y(i+1)+Y(i+2)+Y(i+3)+Y(i+4)+Y(i+5)+Y(i+6))/m;
    T2=(Y(i-4)+Y(i-2)+Y(i-3)+Y(i-1)+Y(i)+Y(i+1)+Y(i+2)+Y(i+3)+Y(i+4)+Y(i+5)+Y(i+6)+Y(i+7))/m;
    T3=(T1+T2)/2;
    kkmo(i)=T3; %αποθηκευση του αθροισματος σε καθε κελι
  end
endfunction
