# Hash function H-384
This hash function is based on Merkle–Damgård construction.
The hash function returns the fingerprint of a string from 0 to the maximum size of the data type integer (2 147 483 647 characters).
In case that the input text is longer, the cut being at a maximum value of integer data type (first 2 147 483 647 characters).
The length of the fingerprint will be 384 bits (64 characters).

## Calculation method
Input text is transformed to binary code. The binary code is completed by other bits
(bit "1" before binary code and binary form of input text length at the end of the binary code).
The length of binary code must be mod 512 = 0. If not the binary code is filled by required number of bits "0".
After this operation the binary code is separated into 512 bit blocks.
Each 512 bit block is separated to eight 64 bit words (unsigned long).
For each block is subsequently performed the words expansion so that the number of words in each block will be 64.
For each word in each block is performed the calculation in the picture.

![alt tag](https://raw.githubusercontent.com/jaromir92/Hash/master/img/schema.png)

A, B, C, D, E, F, K … 64bit word (unsigned long)

UO … unary operation

Wi … input word at index i

x mod … after multiple input words is applied operation mod 2^64 

XOR … bit operation XOR

<< 384 mod … after shiftLeft 384 is applied operation mod 2^64

After calculation the words A .. F are concatenated (in binary representation) to one binary code (6x64bit = 384 bits).
The finally binary code is transformed by dictionary of chars to decimal string (64 characters).