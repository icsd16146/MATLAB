function dkmo = DKMO (Y, n,m)
  kmo = AKMO(Y,n); %���������� ����� ��� ��� ���������� �
  dkmo = AKMO(kmo,m);  %���������� ����� ��� ���������� ��� ���� ��� �
endfunction
