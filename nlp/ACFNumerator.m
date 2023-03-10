function nume = ACFNumerator (y, Yms,pos,n)   
    n3=zeros(n,1);
    n1=y-Yms;
    n2=y;
    n2(1+pos:n)-Yms;
    for i=1:n
      n3(i)=n1(i)*n2(i);
    end
    nume=sum(n3);
end
