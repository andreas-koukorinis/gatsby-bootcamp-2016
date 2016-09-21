
#include <stdlib.h>

void random_ints(int* my_array, int N)
{
	srand(0);

	int i;
	for (i = 0; i < N; i++) {
		my_array[i] = rand();
	}

}