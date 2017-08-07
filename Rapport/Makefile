all: rapport

rapport: 
	pdflatex template_stage_L3 && bibtex template_stage_L3 && pdflatex template_stage_L3 && pdflatex template_stage_L3 

clean:
	rm -f *~ *.aux *.bbl *.blg *.log *.toc
