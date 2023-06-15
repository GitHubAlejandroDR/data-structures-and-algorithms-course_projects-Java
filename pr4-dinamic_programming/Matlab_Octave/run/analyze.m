f = figure;
p = draw('alignment', 'n^2', f);


fprintf(1, 'fit: y=ax^2 + bx + c (red)\n')
fprintf(1, 'Needleman-Wunsch: a=%e, b=%f, c=%f\n', p(1), p(2), p(3));
