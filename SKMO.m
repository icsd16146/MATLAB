function skmo = SKMO (Y,m)
  m = mod(length(Y),2); %=1
  skmo=zeros(length(Y),1);
  a=[0.1, 0.2,  0.4,  0.2,  0.1];
  for(t=1+m:length(Y)-m)
    Yt=Y(t-m:t+m);
    skmo(t,1)= a(2)*Yt(1) + a(3)*Yt(2) + a(4)*Yt(3);
  end
endfunction
