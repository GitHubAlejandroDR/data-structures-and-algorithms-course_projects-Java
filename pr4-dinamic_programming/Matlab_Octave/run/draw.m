function [pa] = draw(label, fit, f)

filename = strcat(label, '.txt');
d = load(filename);

x = d(:,1);
y = mean(d(:,2:end)')';
s = std(d(:,2:end)',1)';

xx = min(x):max(x);

figure(f)
errorbar(x, y, min(y,s), s, 'ko')
xlabel('array size')
ylabel('time (ms)')

if (strcmp(fit,'nlogn')),
    pa = sum(y.*x.*log(x))/(sum((x.*log(x)).^2))
    hold on
    plot (xx, pa*xx.*log(xx), 'b:')
else
    if (strcmp(fit,'n^2'))
        pa = polyfit(x, y, 2);  
        hold on
        plot (xx, polyval(pa, xx), 'r:')
    else
        pa = 0;
    end
end

text(max(x)*1.01, max(y)*1.01, label) 