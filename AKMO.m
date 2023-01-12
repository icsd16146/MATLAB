function akmo = AKMO (Y,n)
  m = mod(n,2);    %=1 %����������� modulo
  tc=zeros(length(Y),1); %���������� ������ tc
  for(t=1:length(Y)) %��� ���� ������� ������ ��� Y
    j=1;
   for(i=-m:m)    
      if(t+m<1 || t+m>59)
      else
        tc(t,j)=Y(t+m);
        j++;
      end
    end
  end
  
  for(i=1:length(Y))
    akmo(i)=sum(tc(i,:))/n;
  end  
endfunction
