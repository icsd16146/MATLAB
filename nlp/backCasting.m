function bc = backCasting (Y,s,f)
  for(i=1:s)
    bc(i)=Y(1); %���������� ������� �����
  end
  for(i=s:length(Y))
    bc(i)=Y(i);  %���������� ����������� �����
  end
  for(i=length(Y):f+length(Y))
    bc(i)=Y(length(Y)); %����������� �����
  end
endfunction
