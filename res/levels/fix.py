import csv

def fix(filename, newfilename):
	map = open(filename)
	data = csv.reader(map)

	dimensions = next(data)

	walls = []
	for row in data:
		walls.append(row)

	fixedWalls = []

	for row in walls:
		fixedWalls.append(row[0] + row[2] + row[1])

	fixedFile = open(newfilename, "a")
	writer = csv.writer(fixedFile)
	writer.writerows(fixedWalls)

	map.close()
	fixedFile.close()

filename = input("Enter file name: ")
newfilename = input("Enter file to write: ")

fix(filename, newfilename)

