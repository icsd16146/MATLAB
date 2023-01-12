function apoEpo = ApoEpo (Y,de,months)
  apoEpo=zeros(59,1);
  j=1;
  for(i=1:59)
    apoEpo(i,1)=(Y(i)/de(j,1))*100;
    j++;
    if(j==months)
      j=1;
    end
  end
  apoEpo;
endfunction
