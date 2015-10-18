### This folder contains three ipython notebooks that are used to process raw heart rate data and build an autoencoder to reconstruct input features for dimensionality reduction. This folder also has a csv file containing the processed data which is the output of notebook 1.

* notebook 1. Afib_Data_Processing: clean data, extract 10-min continuous measurement segments, perform Fourier transformation, and store processed data into a pandas dataframe for analysis steps.

* notebook 2. Afib_Autoencoder: use different dimensionality reduction methods (PCA and multilayer perceptron based autoencoders) to compress input features. In order to evaluate the performance of the autoencoders, L2 loss were calculated, the compressed data were plotted for visualization or fed into a ExtraTree based classifier for predicting afib.

* notebook 3. Afib_ConvNet: build a ConvNet based autoencoder.   