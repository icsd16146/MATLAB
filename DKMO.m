function dkmo = DKMO (Y, n,m)
  kmo = AKMO(Y,n); %δημιουργια απλου κμο της συναρτησης Υ
  dkmo = AKMO(kmo,m);  %δημιουργια απλου κμο συναρτησης του ακμο της Υ
endfunction
