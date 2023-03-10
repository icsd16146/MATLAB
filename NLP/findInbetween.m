function numArray = findInbetween(array,min,max)%ευρεση τιμων πινακα που βρισκονται αναμεσα σε δυο τιμες
    
    format short;

    numArray(length(array/2)) = zeros;
    k=1;
    for i=1:length(array)
        if array(i)>min && array(i)<max
                numArray(k)=array(i);
                k=k+1;
        end
        if array(i)>max
            break;
        end
    end
    new = nonzeros(numArray');
    numArray = reshape(new,[],1)';
end