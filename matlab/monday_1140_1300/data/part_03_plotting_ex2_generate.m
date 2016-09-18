clear

%% Read two books
str_book1 = part_03_plotting_ex2_generate_readbook('subdata/romeo_julia_deutsch.txt');
str_book2 = part_03_plotting_ex2_generate_readbook('subdata/orwell_1984.txt');

%% Remove punctuation (keep only letters numbers and spaces)
str_book1 = part_03_plotting_ex2_generate_remove_punctuation(str_book1);
str_book2 = part_03_plotting_ex2_generate_remove_punctuation(str_book2);


%% Create word-frequency dictionary
[words_book1, counts_book1] = part_03_plotting_ex2_generate_count_word_frequency(str_book1);
[words_book2, counts_book2] = part_03_plotting_ex2_generate_count_word_frequency(str_book2);

save('part_03_plotting_ex2', 'words*','counts*');