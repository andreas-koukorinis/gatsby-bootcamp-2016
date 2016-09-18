function [words, counts] = part_03_plotting_ex2_generate_count_word_frequency( str1 )
%PART_03_PLOTTING_EX2_GENERATE_COUNT_WORD_FREQUENCY Returns the unique
%words and counts for each of them

str1_cell = regexp(lower(str1),' ','split')';
[words, ~, word_locations] = unique(str1_cell); % Find unique words
% Figure out how many of each unique word
counts = accumarray(word_locations,1);

[counts, inds] = sort(counts, 'descend');
words = words(inds);

end

