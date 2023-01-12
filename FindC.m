function c = FindC (kmo,T)
  kmo;
  for(i=1:length(T))
    c(i)=kmo(i+9)/T(i);
  end
endfunction
