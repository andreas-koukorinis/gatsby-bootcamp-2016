function str_book = part_03_plotting_ex2_generate_readbook( file_path )
%READBOOK Reads a plain text file character_by_character;

fid = fopen(file_path);

str_book = '';
k = 0;
while ~feof(fid)
    curr = fscanf(fid,'%c',1);
    if ~isempty(curr)
       k = k+1;
       str_book(k) = curr;
    end
end

fclose(fid);

end

