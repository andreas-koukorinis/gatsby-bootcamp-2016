% SWC-Gatbsy Software Bootcamp 2016
% ---------------------------------------
% Matlab data types and basic operations
% 18/09/2016
% Gergo Bohner

clear
% The following line loads some data into your workspace
load cities


% Understand the data


%% Write a function that
% 1. takes in a string argument
% 2a. If it corresponds to a category -> returns the average rating for that
% category ### Use the built-in Matlab function "strcmp()"
% 2b. If it doesn't it returns -1

category_string = 'climate       '; % What if you don't put all the spaces?
avg_rating = part_02_functions_ex1_func1(category_string, ratings, names, categories);
disp(avg_rating)


%% Write a function that
% 1. Takes in a weighting array (1x9)
% 2. Given that weigthing of category ratings, returns the name of the best
% city to live in

weigth_array = ones(1,9); % Equal weights to every category
best_city = part_02_functions_ex1_func2(weight_array, ratings, names, categories);
disp(best_city)
