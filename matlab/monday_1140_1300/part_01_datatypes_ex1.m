% SWC-Gatbsy Software Bootcamp 2016
% ---------------------------------------
% Matlab data types and basic operations
% 18/09/2016
% Gergo Bohner

clear
clc
% The line below loads a structure S into the workspace
load('data/part_01_datatypes_ex1')


% Explore the structure, learn what kind of data does it represent

all_scores = []; % Create empty array

subjects = {'Cell Biology', 'Machine Learning', 'Building Robots in a week'};

for subjs = 1:3
  for st_id = 1:max([S.student_id]) % Iterates through all student ids
    for i1 = 1:length(S) % Iterate through all entries in S
      % Display the entry if student id is st_id and subject is
      % subjects{subjs}
      if (S(i1).student_id == st_id) && strcmp(S(i1).subject, subjects{subjs}) 
        disp(S(i1))
        all_scores = [all_scores, S(i1).grade];
      end
    end
  end 
end


avg_score = mean(all_scores);

pass = avg_score > 40;

disp(pass)

% Create new representations of the data, that you find more logical



% Which subject seems easiest? (Use the "mean()" function)