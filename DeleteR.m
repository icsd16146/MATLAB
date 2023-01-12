function delR = DeleteR (apoEpo,dkmo,kkmo)
  delR=zeros(59,1);
  delR(1)=((apoEpo(1)+apoEpo(2))/2) + ((kkmo(2)+kkmo(3))/2);
  for(i=2:59)
    delR(i,1)=apoEpo(i,1)/dkmo(1,i);
  end
endfunction
