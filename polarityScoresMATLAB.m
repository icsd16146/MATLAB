%MATLAB tutorials function
function polarity = polarityScoresMATLAB(seeds,vocabulary,wordGraph,depth)

% Remove seeds missing from vocabulary.
idx = ~ismember(seeds,vocabulary);
seeds(idx) = [];

% Initialize scores.
vocabularySize = numel(vocabulary);
scores = zeros(vocabularySize);
idx = ismember(vocabulary,seeds);
scores(idx,idx) = eye(numel(seeds));
p = numel(seeds)
% Loop over seeds.
for i = 1:numel(seeds)

    % Initialize search space.
    seed = seeds(i)
    idxSeed = vocabulary == seed;
    searchSpace = find(idxSeed)
    
    % Search at different depths.
    for d = 1:depth
    
        % Loop over nodes in search space.
        numNodes = numel(searchSpace);
        
        for k = 1:numNodes
            
            idxNew = searchSpace(k);
            
            % Find neighbors and weights.
            nbrs = neighbors(wordGraph,idxNew);
            idxWeights = findedge(wordGraph,idxNew,nbrs);
            weights = wordGraph.Edges.Weight(idxWeights);
            
            % Loop over neighbors.
            for j = 1:numel(nbrs)
                
                % Calculate scores.
                score = scores(idxSeed,nbrs(j));
                scoreNew = scores(idxSeed,idxNew);
                
                % Update score.
                scores(idxSeed,nbrs(j)) = max(score,scoreNew*weights(j));
            end
            
            % Appended nodes to search space for next depth iteration.
            searchSpace = [searchSpace nbrs'];
        end
    end
end

% Find seeds in vocabulary.
[~,idx] = ismember(seeds,vocabulary);

% Sum scores connected to seeds.
polarity = sum(scores(idx,:));

end