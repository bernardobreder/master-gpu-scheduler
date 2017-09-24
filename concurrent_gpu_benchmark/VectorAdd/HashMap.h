#pragma once

struct HashNode {
	int x;
	int y;
	int z;
	int w;
	double value;
	struct HashNode* next;
};

class HashMap {

public:

	HashMap(int dimension, int capacity);
	~HashMap();

	void add(int x, int y, int z, int w, double value);

	double get(int x, int y, int z, int w);

	int size();

private:

	int _dimension;

	int _size;

	int _max;

	struct HashNode** _table;

	unsigned int index(int x, int y, int z, int w);

};

