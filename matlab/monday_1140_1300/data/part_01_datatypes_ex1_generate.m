% SWC-Gatbsy Software Bootcamp 2016
% ---------------------------------------
% Matlab data types and basic operations
% 18/09/2016
% Gergo Bohner

clear

% Create an empty struct:
S = struct('name',{},'student_id',{},'subject',{},'paper_id',{},'grade',{});

% Read name data from csv table
T1 = readtable('subdata/girlstop100s.csv');
T2 = readtable('subdata/boystop100s.csv');

% Get list of popular names
names = {T1.FirstForename{1:30}, T1.FirstForename{1:10}, ...
  T2.FirstForename{1:30}, T2.FirstForename{1:10}}; 
names = names(randperm(length(names))); % Random order, also duplicates exist

% List of subjects taught
subjects = {'Machine learning', 'Cell Biology', 'Building Robots in a week'};
subject_grade_distributions = {{40,120},{60, 70}, {70, 100}}; % Means and variances
subject_number_of_papers = {3, 5, 2};


% Generate horrible struct (each entry is unique)
for n1 = 1:length(names)
  for s1 = 1:length(subjects)
    student_strength_in_subject = round(15*randn(1));% The students individual skill in the subject
    
    for p1 = 1:subject_number_of_papers{s1}
      % Create a new entry for every student's every paper
      S(end+1).name = names{n1};
      S(end).student_id = n1;
      S(end).subject = subjects{s1};
      S(end).paper_id = p1;
      S(end).grade = min(max(round(... 
          student_strength_in_subject + ... % The students individual skill in the subject
          subject_grade_distributions{s1}{1} + ... % Mean of all people
          sqrt(subject_grade_distributions{s1}{2})*randn(1)), ... %Add standard deviation of subject
        0), 100);
    end
  end
end

% Scramble the order of entries
S = S(randperm(length(S)));
      
      
save('part_01_datatypes_ex1','S')

