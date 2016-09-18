function str_out = part_03_plotting_ex2_generate_remove_punctuation( str1 )
%PART_03_PLOTTING_EX2_GENERATE_REMOVE_PUNCTUATION Removes all punctuation
%from a character array, only letters, numbers and spaces remain

% Characters we want to keep
str_space='\s';
str_caps='[A-Z]';
str_ch='[a-z]';
str_nums='[0-9]';

%Their respective positions within str1 are
ind_space=regexp(str1,str_space);
ind_caps=regexp(str1,str_caps);
ind_chrs=regexp(str1,str_ch);
ind_nums=regexp(str1,str_nums);
mask=[ind_space ind_caps ind_chrs ind_nums];


str_out = str1(sort(mask));

end

