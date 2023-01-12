function MO = ExtractMO (array,years,months)
  sorted=zeros(years,months);k=1;
  for(j=1:years)
    for(i=1:months)
      sorted(j,i)=array(k,1);
      k = k+1;
      if(k>59)
        k= k-1;
      end
    end
  end
  sorted;
  
  Max=zeros(months,1);
  Min=zeros(months,1);
  %εδω αποθηκευω το min & max 
  for(j=1:months)
  max=0;
  min=900.000;
    for(i=1:years)
      if(sorted(i,j)>max)
        max=sorted(i,j);
        Max(j,1)=max;
      end
      if(sorted(i,j)>0)
        if(sorted(i,j)<min)
          min=sorted(i,j);
          Min(j,1)=min;
        end
      end
    end
    
  end
  Max;
  Min;
  
  MO=zeros(months,1);
  for(j=1:months)
        MO(j,1)= sum(sorted(:,j)) -Min(j) -Max(j);
  end
  MO./2; 
endfunction
