function b = FindB (TC,T,n,ti,kmo)
  s=zeros(length(kmo),1);
    for(i=1:length(kmo))
      s(i)=i*kmo(i);
    end
  s1=sum(s)/n;
  b1=s1-(T*TC);
  
  b2=(sum(ti.^2)/n)-T^2;
  
  b=b1/b2;  
endfunction
