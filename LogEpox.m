function logEpox = LogEpox (Y,kkmo)
  logEpox=zeros(59,1);
  for(i =1:59)
    logEpox(i,1)=(Y(i)/kkmo(i))*100;
    if(logEpox(i,1)==inf)
      logEpox(i,1)=0;
    end 
  end
  logEpox;
endfunction
