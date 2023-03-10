function nonZeroArray = removeZeros(array)
    nonZeroArray(:,1)=zeros();
    k=1;
    for i =1:length(array)
        if array(i)~= 0
            nonZeroArray(k,1) = array(i);
            k=k+1;
        end
    end
end