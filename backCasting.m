function bc = backCasting (Y,s,f)
  for(i=1:s)
    bc(i)=Y(1); %αποθηκευση αρχικων τιμων
  end
  for(i=s:length(Y))
    bc(i)=Y(i);  %ενδιαμεσες πραγματικες τιμες
  end
  for(i=length(Y):f+length(Y))
    bc(i)=Y(length(Y)); %τελευταιεες τιμες
  end
endfunction
