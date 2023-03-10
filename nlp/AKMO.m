function akmo = AKMO (Y,n)
    akmo=zeros();

    m = mod(n,2)    %υπολογισμος modulo
    for t = m+1:length(Y)-m
        akmo(t) = sum(Y(t-m:t+m));
    end
    akmo = akmo.*(1/n);
end
