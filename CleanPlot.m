function CleanPlot(numArray)
  
  t=1:length(numArray);
  Y1=numArray;

  %backcasting
  start=12-1;
  finish=start+1;
  Y=backCasting(Y1,start,finish);
  
  figure;subplot(3,2,1);plot(Y); title('��������');
  
  n=length(Y);
  
  %������� �����������
  %���� 1
  
  %����� ������� ����� ����
  akmo = AKMO(Y,7);
  hold on;subplot(3,2,2);plot(akmo);hold on;plot(Y);title('����� ������� ����� ����');
  
  %������������ ������� ����� ����
  skmo = SKMO(Y,7);
  hold on;subplot(3,2,3);plot(skmo);hold on;plot(Y);title('������������ ������� ����� ����');
  
  %������ ������� ����� ����
  dkmo1 =DKMO(Y,3,3);
  hold on;subplot(3,2,4);plot(dkmo1);hold on;plot(Y);title('������ ������� ����� ����');
  
  %��������� ������� ����� ����
  kkmo = KKMO(Y,12);  %����� ������ �� ���� backcasting?
  hold on;subplot(3,2,5);plot(kkmo);hold on;plot(Y);title('��������� ������� ����� ����');
  
  hold on;subplot(3,2,6);plot(Y);hold on;plot(akmo);hold on;plot(dkmo1);hold on; plot(skmo);hold on;plot(kkmo);title('��� ����');
  
  
  
  %���� 2
  logepox = LogEpox(Y,kkmo)
  %figure;subplot(2,1,1);plot(logepox);
  
  %���� 3
  mo = ExtractMO(logepox,5,12)  %����� ����
  sk=sum(mo(1:12,1))/1200 %����������� �������������
  de=zeros(12,1);
  de(1,1)=mo(1,1)/sk;
  de(2,1)=mo(2,1)/sk;
  de(3,1)=mo(3,1)/sk;
  de(4,1)=mo(4,1)/sk;
  de(5,1)=mo(5,1)/sk;
  de(6,1)=mo(6,1)/sk;
  de(7,1)=mo(7,1)/sk;
  de(8,1)=mo(8,1)/sk;
  de(9,1)=mo(9,1)/sk;
  de(10,1)=mo(10,1)/sk;
  de(11,1)=mo(11,1)/sk;
  de(12,1)=mo(12,1)/sk; 
  de %������� ������������� 
  
  %���� 4
  apoEpo = ApoEpo(Y,de,12)
  %hold on;subplot(2,1,2);plot(apoEpo);
  
  %���� 5
  dkmo = DKMO(apoEpo,n,3);
  dr = DeleteR(apoEpo,dkmo,kkmo)
  
  %���� 6
  tc=FindTC(kkmo,n)
  t1=FindT1(t,n)
  b=FindB(tc,t1,n,t,kkmo)
  a=FindA(tc,b,t1)
  T=FindT(a,b,t)
  c=FindC(kkmo,T)
  
  %���� 7
  f=FindF(T,de,c,60)  
  Y1(60)=f;
  %hold on;subplot(4,1,4); plot(Y1);
endfunction
