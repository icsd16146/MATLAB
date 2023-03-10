function apoEpo = ApoEpo (Y,de,months)
  apoEpo=zeros(length(Y),1);
  j=1;
  for i=1:length(Y)
    apoEpo(i,1)=(Y(i)/de(j,1))*100;
    j=j+1;
    if j==months
      j=1;
    end
  end
  apoEpo;
end
