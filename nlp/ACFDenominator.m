function denom = ACFDenominator (array, Yms)
  n1=array-Yms;
  n1=n1.^2;
  denom=sum(n1);
end
