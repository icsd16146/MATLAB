function matrix = importData(results, dataLines)
%IMPORTFILE Import data from a text file
%  TWEETSTRINGS = IMPORTFILE(FILENAME) reads data from text file
%  FILENAME for the default selection.  Returns the data as a string
%  array.
%
%  TWEETSTRINGS = IMPORTFILE(FILE, DATALINES) reads data for the
%  specified row interval(s) of text file FILENAME. Specify DATALINES as
%  a positive scalar integer or a N-by-2 array of positive scalar
%  integers for dis-contiguous row intervals.
%

%  Example:
%  tweetstrings = importfile("C:\Users\konstantina\Desktop\MATLAB\diplomatiki\nlp\tweet_strings.txt", [2, Inf]);
%
%  See also READMATRIX.
%
% Auto-generated by MATLAB on 11-Dec-2022 18:08:59

%% Input handling

% If dataLines is not specified, define defaults
if nargin < 2
    dataLines = [2, Inf];
end

%% Set up the Import Options and import the data
opts = delimitedTextImportOptions("NumVariables", 1);

% Specify range and delimiter
opts.DataLines = dataLines;
opts.Delimiter = ",";

% Specify column names and types
opts.VariableNames = "VarName9";
opts.VariableTypes = "string";

% Specify file level properties
opts.ExtraColumnsRule = "ignore";
opts.EmptyLineRule = "read";

% Specify variable properties
opts = setvaropts(opts, "VarName9", "WhitespaceRule", "preserve");
opts = setvaropts(opts, "VarName9", "EmptyFieldRule", "auto");

% Import the data
matrix = readmatrix(results, opts);

end